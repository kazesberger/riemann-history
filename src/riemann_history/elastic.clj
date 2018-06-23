(ns riemann-history.elastic
  (:require [clj-time.core :as t]
            [clj-time.periodic :refer [periodic-seq]]
            [cheshire.core :as cheshire]
            [clojure.core.async :as a]
            [tea-time.core :as tt]
            [chime :refer :all]))

(let [chimes (chime-ch [(-> 2 t/seconds t/from-now)
                        (-> 3 t/seconds t/from-now)])]
  (a/<!! (go-loop []
                  (when-let [msg (<! chimes)]
                    (prn "Chiming at:" msg)
                    (recur)))))

(chime-ch (take 5 (periodic-seq
                    (t/now)
                    (-> 5 t/minutes))))


;(def fred-durst (tt/every! 10 2 (bound-fn [] (prn "THAT is a thing"))))

;;;;;;;;;;;;;;;;;;;;;;
(require 'clojure.java.io)
(def query-result
  (cheshire/parse-string
    (slurp (clojure.java.io/resource "elasticsearch/result.example"))
    keyword))
;;;;;;;;;;;;;;;;;;

