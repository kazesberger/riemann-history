(defproject riemann-history "0.1.0-SNAPSHOT"

  :description "riemann-history"
  :url "http://github.com/infonova/riemann-history"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[cc.qbits/spandex "0.6.2"]
                 [cheshire "5.8.0"]
                 [tea-time "1.0.0"]
                 [org.clojure/tools.logging "0.4.1"]]

  :profiles {:dev {:dependencies [[riemann "0.3.1"]
                                  [org.clojure/clojure "1.9.0"]]}}

  :resource-paths ["resources" "target/resources"])
