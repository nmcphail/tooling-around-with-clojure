 (ns http-server-test.core
    (:require  
     [org.httpkit.server :as serv ]
      ))


(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(defonce server (atom nil))

 (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server)
    (reset! server nil))


;;(reset! server (serv/run-server app {:port 8090}))









;;(@server)

