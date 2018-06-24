(ns riemann-history.core
  (:require [clojure.tools.logging :as log]
            [cheshire.core :as json]
            [qbits.spandex :as es]
            [riemann.time :refer [every!]]))

(def history-data 
  (atom {}))

(defn get-query
  "Get the Elasticsearch query from file."
  [query-file]
  (json/parse-string (slurp query-file) true))       ; TODO abstraction (either get-query-elastic etc. or json/sql)

(defn query
  "Query Elasticsearch and update the history-data map."
  [client config]
  (let [query (get-query (:query config))
        response (es/request client {:url (:url config "_search")
                                     :method :get
                                     :body query})
        buckets (get-in response [:body :aggregations :total_requests_per_day_hour :buckets])]
    (reset! history-data buckets)))

(defn history
  "Takes one parameter:

  `config`: A map containing the following properties
  
  `:connect`    Elasticsearch `:connect` (default `http://localhost:9200`).
  `:url`        Elasticsearch context (default `_search`)
  `:interval`   Interval for querying the `:source` (default `86400` seconds).
  `:query`      Path to the query file.

  "
  [config]
  (let [client (es/client {:hosts [(:connect config "http://localhost:9200")]})]                  ; TODO abstraction
    (every! (:interval config 86400) 10 (query client config))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; EOF ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (history {:connect "http://localhost:9200"
            :url "_search"
            :interval 600
            :query "/etc/riemann-history/elasticsearch.json"})


  (require 'clojure.java.io)
  (def query-result
    (cheshire/parse-string
      (slurp (clojure.java.io/resource "elasticsearch/sample/result.json"))
      keyword)))