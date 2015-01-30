(defproject mind-strom "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [com.h2database/h2 "1.3.170"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [incanter/incanter-core "1.4.1"]
                 [incanter/incanter-excel "1.4.1"]
                 [dk.ative/docjure "1.8.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler mind-strom.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}
  :main mind-strom.handler
  :aot [mind-strom.handler])
