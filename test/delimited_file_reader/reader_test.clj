(ns delimited-file-reader.reader-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [delimited-file-reader.reader :as r]))

(def pipe-file (r/open-file "test/pipe.txt"))
(def comma-file (r/open-file "test/comma.txt"))
(def space-file (r/open-file "test/space.txt"))

(pprint (r/load-data pipe-file))
