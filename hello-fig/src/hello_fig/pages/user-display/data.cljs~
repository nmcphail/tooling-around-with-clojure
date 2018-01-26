(ns hello-fig.pages.user-list.data
  (:require  [quiescent.core :as q]
             [quiescent.dom :as d]
             [quiescent.dom.uncontrolled :as du]
             [goog.dom.classlist]
                          ))



(def rows (atom [{:row-properties {:selected false :modified false :inline-edit false} :data {:id 100 :firstname "Neil" :lastname "McPhail"}}
                 {:row-properties {:selected true  :modified false :inline-edit false} :data {:id 200 :firstname "Bruce" :lastname "Mcphail"}}
                 {:row-properties {:selected false :modified false :inline-edit false} :data {:id 300 :firstname "Anne" :lastname "McPhail"}}]))

(defn populate-rows [num-rows]

  (doseq [ix  (range num-rows)]
    (let [x {:row-properties
             {:selected false :modified false :inline-edit false }
             :data {:id ix :firstname (str "Firstname " ix ) :lastname (str "Lastname " ix )  }}]

;;      (println x)
      (swap! rows conj x ))))

(populate-rows 20)

(defn set-inline-edit-for-each-row [ inline-edit]
  (loop
       [ix 0
       [row & rest ] @rows]
    
    (do
      (swap! rows assoc-in [ix :row-properties :inline-edit ] inline-edit )
      (if (not (empty? rest )) (recur (inc ix) rest  )))))

(defn find-ix-matching-id [id-looking-for rows]
  (loop
      [ix 0
       [row & rest ] rows]
    (let [{ {id :id} :data }  row ]
      (do

        (if-not (= id id-looking-for ) 
          (if (not (empty? rest ))
            (do
              ;;(println ix )
              (recur (inc ix) rest  ))
            )
            ix)))))



