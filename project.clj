(defproject julia-set "0.1.0-SNAPSHOT"
  :description "Drawing Julia set in ClojureScript"
  :url "http://github.com/bkruczyk/julia-set"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]]
  :plugins [[lein-cljsbuild "1.1.3"]]
  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:id "dev"
                                   :main "julia-set.core"
                                   :optimizations :none
                                   :pretty-print true}}]})
