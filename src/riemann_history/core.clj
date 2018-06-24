(ns riemann-history.core)

(defonce db  (atom {}))

(defn history
  "Just messing around..."
  [event]
  (str "riemann-history: db last-updated on " ((:last-updated @db))))

