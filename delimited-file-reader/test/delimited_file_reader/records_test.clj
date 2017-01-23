(ns delimited-file-reader.records-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.records :refer :all])
  (:import [delimited_file_reader.records Person]))

(def valid-person (Person. "Hall" "Mike" "M" "12/19/1975" "Blue"))

(deftest valid-person-test
  (testing "A Person can be populated with valid data"
    (is (= (get valid-person :last_name) "Hall"))
    (is (= (get valid-person :first_name) "Mike"))
    (is (= (get valid-person :gender) "M"))
    (is (= (get valid-person :date_of_birth) "12/19/1975"))
    (is (= (get valid-person :favorite_color) "Blue"))))

