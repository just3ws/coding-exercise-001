(defproject delimited-file-reader "0.1.0-SNAPSHOT"
  :description "Consume files that can either be pipe, comma, or space delimited."
  :url "http://github.com/just3ws/coding-exercise-001"
  :license {
            :name "GNU General Public License version 3"
            :url "https://www.gnu.org/licenses/gpl-3.0.txt" }
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-time "0.13.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [semantic-csv "0.1.1-just3ws"] ;; Because :transform-header
                 [camel-snake-kebab "0.4.0"]]
  :main ^{ :skip-aot true } delimited-file-reader.runner
  :aot [delimited-file-reader.runner]
  ;: main delimited-file-reader.core
  ;; :aot [delimited-file-reader.core]
  :plugins [[cider/cider-nrepl "0.14.0"] ;; Recommended for use with vim-fireplace
            [lein-cloverage "1.0.10-SNAPSHOT"] ;; Test coverage analysis
            [com.jakemccrary/lein-test-refresh "0.18.1"] ;; Run tests on change
            [lein-kibit "0.1.3"] ;; Clojure static analysis
            [lein-ancient "0.6.10"] ;; Check for out-of-date dependencies
            [jonase/eastwood "0.2.3"] ;; Clojure linting
            [lein-localrepo "0.5.3"]
            [venantius/ultra "0.5.1"] ;; Improved test output, repl printing
            [lein-cljfmt "0.5.6"]
            [lein-pprint "1.1.2"]] ;; Pretty printing
  :test-selectors {
                   :default (complement :slow)
                   :focus :focus
                   :slow :slow
                   :all (constantly true) })
