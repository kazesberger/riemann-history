(defproject riemann-history "0.1.0-SNAPSHOT"

  :description "riemann-history"
  :url "tbd"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[clojurewerkz/elastisch "3.0.0"]
                 [jarohen/chime "0.2.2"]]


  :profiles {:dev {:dependencies [[riemann "0.3.1"]
                                  [org.clojure/clojure "1.9.0"]]}}

  :resource-paths ["resources" "target/resources"])
