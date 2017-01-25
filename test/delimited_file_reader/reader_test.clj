(ns delimited-file-reader.reader-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.reader :as r]))

(deftest date-parsing-test
  (is (= "12/19/1975" (r/write-date (r/read-date "12/19/1975"))))
  (is (= "7/6/1975" (r/write-date (r/read-date "7/6/1975"))))
  (is (= false (r/date? "hello")))
  (is (= true (r/date? "7/6/1975"))))
