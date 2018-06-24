(ns riemann-history.elastic
  (:require [riemann-history.core :as core]
            [cheshire.core :as cheshire]
            [tea-time.core :as tt]))



(def fred-durst
  (tt/every! 10 2 (bound-fn [] (println "THAT is a thing"))))

;;;;;;;;;;;;;;;;;;;;;;
(require 'clojure.java.io)
(def query-result
  (cheshire/parse-string
    (slurp (clojure.java.io/resource "elasticsearch/result.example"))
    keyword))
;;;;;;;;;;;;;;;;;;



(comment (let [chimes (chime-ch [(-> 2 t/seconds t/from-now)
                                 (-> 3 t/seconds t/from-now)])]
           (a/<!! (a/go-loop []
                    (when-let [msg (a/<! chimes)]
                      (swap! core/db assoc :last-updated t/now)
                      (recur)))))

         (chime-ch (take 5 (periodic-seq
                             (t/now)
                             (-> 5 t/minutes)))))
