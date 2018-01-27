(ns http-server-test.data-access-test
  (:require [clojure.test :refer :all]
             [clojure.pprint :as pp]
            [http-server-test.data-access :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= (get-user-by-id 1)
           {:id 1, :first-name "Fisrtname 1", :last-name "last name 1"}))))

(deftest b-test
  (testing "FIXME, I fail."
    (let [users (get-entities-user nil)  ]
            
      (is (= users

[{:id 0, :first-name "Fisrtname 0", :last-name "last name 0"}
 {:id 1, :first-name "Fisrtname 1", :last-name "last name 1"}
 {:id 2, :first-name "Fisrtname 2", :last-name "last name 2"}
 {:id 3, :first-name "Fisrtname 3", :last-name "last name 3"}
 {:id 4, :first-name "Fisrtname 4", :last-name "last name 4"}
 {:id 5, :first-name "Fisrtname 5", :last-name "last name 5"}
 {:id 6, :first-name "Fisrtname 6", :last-name "last name 6"}
 {:id 7, :first-name "Fisrtname 7", :last-name "last name 7"}
 {:id 8, :first-name "Fisrtname 8", :last-name "last name 8"}
 {:id 9, :first-name "Fisrtname 9", :last-name "last name 9"}]
             )
        )    
    ) #_(pp/pprint (get-entities-user nil) )))




(run-tests)
