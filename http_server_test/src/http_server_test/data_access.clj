 (ns http-server-test.data-access
    (:require  
     [clojure.pprint :as pp]
     [clojure.java.io :as io]))


(defn get-user-by-id [id]
  {:id id
   :first-name (str "Fisrtname " id )
   :last-name (str "last name " id )})

(defn get-entities-user  [ filter ]
  (reduce (fn [ acc id ] (conj acc
                       {:id id
                        :first-name (str "Fisrtname " id )
                        :last-name (str "last name " id )})) [] (range 10 )))







;;(@server)

