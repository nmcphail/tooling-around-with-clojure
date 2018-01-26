(ns hello-fig.core
  (:require  
   [hello-fig.pages.user-list.display :as page-user-list]
   [hello-fig.pages.user-display.display :as page-user-display]
   [fsmviz.core :as fsmviz]))

(enable-console-print!)

;;(page-user-list/render-grid)
;;(page-user-display/render-page)




(def app-state (atom {:text "Hello world!"
                          :hibernate-stack [] }))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
(defn page-state-change [ {:keys [page-name state-from state-to transition restore-function] :as state-change-data }  ]
  (println (str "page name: " page-name ", transition:  " transition  ", from state:  " state-from ", to state: " state-to))
  (if (= transition :display-row )
    (do
      (swap! app-state update-in [:hibernate-stack] conj restore-function)
      (println "ddd" (:user state-change-data) )
      (page-user-display/setup (:user state-change-data) page-state-change )))
  (if (and (= page-name "user-display" ) (= transition :quit))
    (do
      (let [ restore-func (last (get-in @app-state [:hibernate-stack])) ]
           (restore-func)
       )
      (swap! app-state update-in [:hibernate-stack] pop)
      ;;(page-user-list/setup nil nil page-state-change)
      ))

  )



;;(page-user-display/render-page)

(page-user-list/setup nil nil page-state-change)

(defn switch-to-user-edit []
 (page-user-display/render-page) )

