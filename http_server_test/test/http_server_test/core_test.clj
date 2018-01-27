(ns http-server-test.core-test
  (:require [clojure.test :refer :all]
            [http-server-test.core :refer :all]
            [org.httpkit.client :as http]
              [clojure.pprint :as pp]))

(deftest a-test
  (testing "FIXME, I fail."


    (let [ options
            #_{:body (str '(http-server-test.data-access/get-user-by-id 1))}
          {:body  (str '(http-server-test.data-access/get-user-by-id 1))}
            #_{:form-params {:name "http-kit" :features ["async" "client" "server"]}}
          {:keys [status error] :as res } @(http/post "http://localhost:8090"  options)]
     (if error
       (println "Failed, exception is " error)
       (pp/pprint
        (eval (read-string (:body  res))) )))
    (is (= 1 1))))


(deftest b-test
  (testing "FIXME, I fail."


    (let [ options
            #_{:body (str '(http-server-test.data-access/get-user-by-id 1))}
          {:body  (str '(http-server-test.data-access/get-entities-user 1))}
            #_{:form-params {:name "http-kit" :features ["async" "client" "server"]}}
          {:keys [status error] :as res } @(http/post "http://localhost:8090"  options)]
     (if error
       (println "Failed, exception is " error)
       (pp/pprint
        (eval (read-string (:body  res))) )))
    (is (= 1 1))))






(run-tests)
