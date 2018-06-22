(ns riemann-history.elastic
  (:require [clj-time.core :as t]
            [clj-time.periodic :refer [periodic-seq]]
            [chime :as chime]))
;

(take 5 (periodic-seq
          (t/now)
          (-> 5 t/minutes)))

;(take 5 (-> 2 t/seconds t/from-now))
