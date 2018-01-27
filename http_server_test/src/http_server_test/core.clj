 (ns http-server-test.core
    (:require  
     [org.httpkit.server :as serv ]
     [clojure.pprint :as pp]
     [clojure.java.io :as io]))


(defn app [req]
  (def response-template  {:status  200
                           :headers {"Content-Type" "text/html"}
                           :body    "hello HTTP!"})

  (with-open [rdr (io/reader (:body req ) :encoding "UTF-8"   )]
    (let [s (slurp rdr )]
      (assoc response-template :body  ( str (eval (read-string  s))))
      #_(pp/pprint s)
      #_(assoc response-template :body s)
      )))

  

(defonce server (atom nil))

 (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server)
    (reset! server nil))


(reset! server (serv/run-server app {:port 8090}))









;;(@server)

