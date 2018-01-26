(ns hello-fig.components.dropdown
  (:require  [quiescent.core :as q]
             [quiescent.dom :as d]
             [quiescent.dom.uncontrolled :as du]
             [goog.dom.classlist]
             ))

(defn get-dropdown-node [id]
   (.getElementById js/document id))

(defn hide-dropdown [ id ]
  (let [dom-node (get-dropdown-node id) ]
     (goog.dom.classlist/remove  dom-node "show")))

(defn toggle-dropdown [ id ]
  (let [dom-node (get-dropdown-node id) ]
     (if (goog.dom.classlist/contains dom-node "show" )
         (goog.dom.classlist/remove  dom-node "show")
         (goog.dom.classlist/add     dom-node "show"))))


(q/defcomponent DropDown
  [dropdown-data]
  (let [uuid (random-uuid)
        {name :name } dropdown-data
        {menu-items :menuitems } dropdown-data
        ]
    (d/div {:className "dropdown"}
           (d/button {:className "dropbtn"
                      :onClick (fn [evt] (toggle-dropdown uuid)    )} name    )

           
           (d/div {:id uuid :className "dropdown-content"}
                  (map (fn [d]  (d/a {:href "#"  :onClick

                                      (comp (:function d) (partial toggle-dropdown uuid))
                                      ;;(:function d)

                                      }
                                     (:display d))) menu-items)))))


