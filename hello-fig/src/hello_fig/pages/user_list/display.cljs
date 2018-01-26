(ns hello-fig.pages.user-list.display
  (:require  [quiescent.core :as q]
             [quiescent.dom :as d]
             [quiescent.dom.uncontrolled :as du]
             [goog.dom.classlist]
             [hello-fig.modal :as m]
             [hello-fig.components.dropdown :as dd ]
             [hello-fig.pages.user-list.data :as page-data ]
             [hello-fig.pages.user-list.state :as page-state ]))

(enable-console-print!)


(defn get-user-for-row-index [row-index]
  (get-in @page-data/rows [ row-index :data  ]))


(def grid-state (atom {:inline-edit true }))

(defn on-click
  [e]
  (.preventDefault e)
  (println "clicked "))


;;(def field-function-map {:field-1 :lastname  :field-2 :firstname } )

(def fields [:lastname  :firstname]  )

(defn edit-field
  [fieldnum row-index val]
  (let [field (fields fieldnum )]
        (swap! page-data/rows assoc-in [row-index :data field] val)))


(defn render-field-for-edit
  [field-num field-value row-index  ]
    (du/input { :value (str field-value) :onChange
                            (fn [evt] (let [new-val (.-value (.-target evt)) ]  (edit-field field-num row-index new-val  )))}))
(defn render-field-for-display
  [field-num field-value id  ]
    (d/div { :onClick on-click } (str field-value))
    )


(defn edit-row [row-index]
  (println (str "edit row " row-index))
  (page-state/transition-display-display-row-hibernate row-index )
  )


(q/defcomponent Row
  :keyfn (fn [value] (str (get-in value [:data :id ])) )
  [row row-index grid-state]
  (let [ {row-props :row-properties
          data :data } row
        {id :id
         field0 (fields 0)
         field1 (fields 1) } data
        field-render-function (if (:inline-edit row-props)   render-field-for-edit render-field-for-display) ]
 
     (d/tr  { :id (str id )  :className "row" :onClick (partial edit-row row-index)   }
     (d/td { :className "row-select"}  (d/input {:type "checkbox" :checked (:selected row-props) :onChange (fn [evt] (println "hello" ))  }))
     (d/td {:className "row-field"} (field-render-function 0 field0 row-index)  )
     (d/td {:className "row-field"} (field-render-function 1 field1 row-index) )
     (d/td {:className "row-edit"} (d/a { :href "#"  :onClick on-click  }  "edit") )
     (d/td {} (d/div {} (str row-index ))))))






(declare render-grid)

(defn toggle-inline-edit
  []
  (let [{inline-edit :inline-edit} @grid-state
        new-inline-edit (not inline-edit)] 
    (swap! grid-state assoc-in [:inline-edit ] new-inline-edit  )
    (page-data/set-inline-edit-for-each-row new-inline-edit )
    (render-grid)
    ))




(def dropdown1 {   :name "Dropdown 1"
                   :menuitems [{:display "Display Row"
                                :function (fn [] (page-state/transition-display-display-row-hibernate 1 ) )} 
                               {:display "DD1 Link2"
                                :function (fn [] (m/render-modal) )}]})
(def dropdown2 {   :name "Dropdown 2"
                   :menuitems [{:display "DD2 Link1"
                                :function (fn [evt & args] (println (str evt)) )} 
                               {:display "DD2 Link2"
                                :function (fn [evt & args] (println (str args )) )}]})




(q/defcomponent RowList
   [rows grid-state]
  (d/table {:className "rows"}
           (d/thead {:className "rows-head"}
                    (d/tr { :className "rows-buttons"}   
                          (d/th { :colSpan "9995"}
                                ""
                                ;; (d/button { :onClick (fn [evt]
                                ;;                        (toggle-inline-edit)
                                ;;                        )} (if (:inline-edit grid-state)  "Display" "Edit"))
                                ;; (d/button { :onClick (fn [evt] (render-grid))} "Refresh")
                                (dd/DropDown  dropdown1 )
                                ;;(dd/hello)
                                ;;(DropDown dropdown2 )                                
                                ))
                    )
            (d/tbody { :className "rows-body"}
                (map-indexed (fn [ix row grid-state]  (Row row ix grid-state ) ) rows))))

(defn render-grid []
  (println "re-rendering")
  (q/render (RowList @page-data/rows @grid-state)
            (.getElementById js/document "app")))

(defn setup [rows state state-change-callback ]
  (page-state/transition-start-setup-display  nil state-change-callback render-grid )
)



