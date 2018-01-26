(ns hello-fig.modal
  (:require  [quiescent.core :as q]
             [quiescent.dom :as d]
             [quiescent.dom.uncontrolled :as du]
             [goog.dom.classlist]))


(declare clear-modals)

(q/defcomponent Modal
  [ val ]
  (d/div {:id "modal" :className "a-modal show"}
         (d/div {:className "modal-content" } (d/p {} "Some Text")
                (d/div {:className "modal-footer"}
                        (d/button { :onClick clear-modals} "Some Text in footer")
                        ))))

(q/defcomponent HideModal
  [ val ]
  (d/div {}))


(defn clear-modals []
(q/render (HideModal 1)
              (.getElementById js/document "modalwindows"))
)


(defn render-modal []
  (println "rendereing modal")

  (if (= 1 1 )    
    (q/render (Modal 1)
              (.getElementById js/document "modalwindows"))
    (q/render (HideModal 1)
              (.getElementById js/document "modalwindows"))))

