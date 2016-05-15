(defproject julia-set "0.1.0-SNAPSHOT"
  :description "Drawing Julia set in ClojureScript"
  :url "http://github.com/bkruczyk/julia-set"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [com.cemerick/piggieback "0.2.1"]
                 [figwheel-sidecar "0.5.2"]]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :plugins [[lein-figwheel "0.5.1"]]
  :source-paths ["src" "src-cljs"]
  :clean-targets ^{:protect false} ["resources/public/js" :target]
  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src-cljs"]
                        :figwheel true
                        :compiler {:main "julia-set.core"
                                   :asset-path "js/out"
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/out"
                                   :source-map true
                                   :source-map-timestamp true}}]})
