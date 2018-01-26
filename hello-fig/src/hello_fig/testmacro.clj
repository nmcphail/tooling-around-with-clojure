(ns hello-fig.testmacro
  )

(defmacro hello [a1]
  ;;(println "hel lo from inisde a macro")
  (println (str "inside macro " a1 ))
  `(defn ~(symbol  (str "funct" a1)) [~'w] (str "wwwwwww" ~'w ))
;;  (def b "eee")
;;  `(println  ~(str "aaa" a1   ))
  )

(defmacro print-something [] '(println "something"))



;;
(doseq [x (range 10) ] (do (println x ) (hello x)) )


;;(print-something )




;;(macro-expand 'hello)


