(ns hello-fig.pages.user-list.state
  (:require
             [fsmviz.core :as fsmviz]
             [hello-fig.modal :as m]
             [hello-fig.components.dropdown :as dd ]
             [hello-fig.pages.user-list.data  :as page-data ]

             ))

(def page-name "user-list")

(def state_fsm {:start {:setup :display }
                :display {:display-row :hibernate :quit :end }
                })

(def state (atom {:current-state :start
                  :state-change-callback nil
                  :render-function nil}
                  :row-index-to-edit nil ))


(defn add-state-change-callback [callback]
  (swap! state assoc-in [:state-change-callback] callback  )
)


(defn call-state-change-callback [page-name state-from state-to transition restore-function ]
  ((:state-change-callback @state ) {:page-name  page-name
                                     :state-from  state-from
                                     :state-to  state-to
                                     :transition  transition
                                     :restore-function restore-function} ))

(defn transition-start-setup-display [rows state-change-callback render-func ]
  (swap! state assoc-in [:current-state] :start)
  (let [transition :setup
        current-state (:current-state @state)
        new-state :display ]
    (add-state-change-callback state-change-callback )
    (call-state-change-callback page-name current-state new-state transition nil ) 
    (swap! state assoc-in [:current-state ] new-state )
    (swap! state assoc-in [:render-function] render-func  )
    ((:render-function @state))))


(defn transition-hibernate-restore-display [ restored-state restored-users]
  (reset! state restored-state)
  (println "restored state " @state )
  
  (let [transition :restore
        current-state :hibernate
        new-state :display ]
    (
     (swap! state assoc-in [:current-state] new-state)
     (:state-change-callback @state ) {:page-name  page-name
                                       :state-from  current-state
                                       :state-to  new-state
                                       :transition  transition
                                       
                                             } )
    ((:render-function @state))))


(defn transition-display-display-row-hibernate [ row-index-to-edit ]
  (let [transition :display-row
        current-state (:current-state @state)
        new-state :hibernate ]
    (swap! state assoc-in [:current-state ] new-state )
    (swap! state assoc-in [:row-index-to-edit ] row-index-to-edit )
    (call-state-change-callback page-name current-state new-state transition (partial transition-hibernate-restore-display @state nil ) )
    ((:state-change-callback @state ) {:page-name  page-name
                                       :state-from  current-state
                                       :state-to  new-state
                                       :transition  transition
                                       :restore-function  (partial transition-hibernate-restore-display @state nil )
                                       :user  (get-in @page-data/rows [ row-index-to-edit :data  ])      } )))



(defn print-state  [ ]
 (fsmviz.core/generate-image {:start {:t1 :foo
                                     :t2 :bar}
                             :foo {:t3 :baz}
                             :bar {:t4 :baz}} "example-map") )




