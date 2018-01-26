(ns hello-fig.pages.user-display.state
  (:require
   [hello-fig.components.dropdown :as dd ]
   [hello-fig.pages.user-display.data :as page-data ]))


(def page-name "user-display")

(def state_fsm {:start {:setup :display }
                :display { :quit :end }
                })

(def state (atom {:current-state :start :state-change-callback nil :render-function (fn [] )}  ))


(defn add-state-change-callback [callback]
  (swap! state assoc-in [:state-change-callback] callback  )
)


(defn call-state-change-callback [page-name state-from state-to transition ]
  ((:state-change-callback @state ) {:page-name  page-name
                                     :state-from  state-from
                                     :state-to  state-to
                                     :transition  transition} ))

(defn transition-start-setup-display [user state-change-callback render-func]

  (swap! state assoc-in [:current-state] :start)
  (let [ks (keys user)
        fs [ {:field-name "fff" :field-value "wwww"} ]]
    (println (reduce (fn [acc f ] (conj acc (assoc {} f (f user) ) )  ) [] ks  ))
    (reset! page-data/user (reduce (fn [acc f ] (conj acc (assoc {} :field-name f :field-value  (f user) ) )  ) [] ks  ) ))
  (let [transition :setup
        current-state (:current-state @state)
        new-state :display ]
    (add-state-change-callback state-change-callback )
    (swap! state assoc-in [:current-state ] new-state )
    (swap! state assoc-in [:render-function] render-func )
    ((:render-function @state))
    (call-state-change-callback page-name current-state new-state transition)))

(defn transition-display-quit-end []
  (let [transition :quit
        current-state (:current-state @state)
        new-state :end ]
    (swap! state assoc-in [:current-state ] new-state )
    (call-state-change-callback page-name current-state new-state transition )))





