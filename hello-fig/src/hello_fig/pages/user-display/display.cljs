(ns hello-fig.pages.user-display.display
  (:require  [quiescent.core :as q]
             [quiescent.dom :as d]
             [hello-fig.pages.user-display.data :as page-data ]
             [hello-fig.modal :as m]
             [hello-fig.components.dropdown :as dd ]
             [hello-fig.pages.user-display.state :as page-state ]
             [hello-fig.pages.user-display.data :as page-data ]))

(enable-console-print!)

(q/defcomponent Field
;;  :keyfn (fn [value]  (:field-name value ) )
  [field]
  (let [ { :keys [field-name field-value]} field]
 
     (d/tr  {  :className "row"   }
            (d/td {:className "field-name"} (d/div {} (str field-name ":" )))
            (d/td {:className "field-value"} (d/div {} (str field-value )))
            )))



(defn click-cancel [evt] (page-state/transition-display-quit-end))

(q/defcomponent DisplayGrid
  [fields]
    (d/table {:className "field-display"}
           (d/thead {:className "field-display-head head"}
                    (d/tr { :className "menu-buttons"}   
                          (d/th { :colSpan "9995"}    (d/button { :onClick click-cancel } "Caaacel") )))
            (d/tbody { :className "field-display-body"}
                     (map (fn [field ]  (Field field) ) fields)
                     (d/tr {:className "row"}    ))))




(defn render-page []
  (q/render (DisplayGrid @page-data/user )
            (.getElementById js/document "app")))


(defn setup [user state-change-callback ]
  (page-state/transition-start-setup-display  user state-change-callback render-page )
)
