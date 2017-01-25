(ns delimited-file-reader.date-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.date :as d]))

(deftest date-parsing-test
  (is (= "12/19/1975" (d/write-date (d/read-date "12/19/1975"))))
  (is (= "7/6/1975" (d/write-date (d/read-date "7/6/1975"))))
  (is (false? (d/date? "hello")))
  (is (true? (d/date? "7/6/1975"))))
