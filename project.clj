(defproject plik "0.2.0-SNAPSHOT"
  :description "Consume files that can either be pipe, comma, or space delimited."
  :url "http://github.com/just3ws/coding-exercise-001"
  :min-lein-version "2.8.1" ;; Prefer ~> 2.8.1 but Heroku has only 2.7.1
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; Data App
                 [clj-time "0.14.2"]
                 [clojure-csv "2.0.2"]
                 [org.clojure/data.csv "0.1.4"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/data.json "0.2.6"]
                 [semantic-csv "0.2.0"]
                 [camel-snake-kebab "0.4.0"]
                 ;; Web App
                 [compojure "1.6.0"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [environ "1.1.0"]]
  :profiles {:parser {:main ^{:skip-aot true} plik.parser.runner
                      :aot [plik.parser.runner]}
             :web {:main plik.web.core
                   :aot :all
                   :production {:env {:production true}}
                   :dev {:dependencies [[javax.servlet/servlet-api "4.0.0"]
                                        [ring/ring-mock "0.3.2"]]}

                   }}
  :aliases {"parser" ["with-profile" "parser" "run"]
            "web" ["with-profile" "web" "run"]}
  :plugins [[cider/cider-nrepl "0.16.0"] ;; Recommended for use with vim-fireplace
            [lein-cloverage "1.0.10"] ;; Test coverage analysis
            [com.jakemccrary/lein-test-refresh "0.22.0"] ;; Run tests on change
            [lein-kibit "0.1.6"] ;; Clojure static analysis
            [lein-ancient "0.6.15"] ;; Check for out-of-date dependencies
            [jonase/eastwood "0.2.5"] ;; Clojure linting
            [lein-localrepo "0.5.4"]
            [venantius/ultra "0.5.2"] ;; Improved test output, repl printing
            [lein-cljfmt "0.5.7"] ;; Linting/style checking
            [lein-pprint "1.2.0"] ;; Pretty printing
            [lein-environ "1.1.0"]
            [lein-ring "0.12.3"]]
  :uberjar-name "plik.jar"
  :repl-options {:port 44444}
  :test-selectors {:default (complement :slow)
                   :focus :focus
                   :slow :slow
                   :all (constantly true)})
