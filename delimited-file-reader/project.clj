(defproject delimited-file-reader "0.1.0-SNAPSHOT"
  :description "Consume files that can either be pipe, comma, or space delimited."
  :url "http://github.com/just3ws/coding-exercise-001"
  :license {:name "GNU General Public License version 3"
            :url "https://www.gnu.org/licenses/gpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main delimited-file-reader.core
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.18.1"]
                             [venantius/ultra "0.5.1"]]}})
