(defproject org.roman01la/scrum.router "0.1.0-SNAPSHOT"
  :description "Router for Scrum"
  :url "https://github.com/roman01la/scrum"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.456" :scope "provided"]
                 [org.roman01la/scrum "0.1.0-SNAPSHOT" :scope "provided"]
                 [rum "0.10.8" :scope "provided"]
                 [funcool/bide "1.4.0"]
                 [kibu/pushy "0.3.6"]]

  :plugins [[lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src" "example"]
                :compiler {:main app.core
                           :asset-path "target/dev"
                           :output-to "target/app.js"
                           :output-dir "target/dev"
                           :compiler-stats true
                           :parallel-build true}}]})
