(ns delimited-file-reader.records-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.records :refer :all]
            [delimited-file-reader.date :as d])
  (:import [delimited_file_reader.records Person]))


;; Directly initialize Person defrecord
(testing "Directly generate a Person record"
  (def favorite_color "green")
  (def date_of_birth "7/6/1975")
  (def gender "F")
  (def first_name "Aneta")
  (def last_name "Hall")

  (def valid_person (Person. last_name first_name gender date_of_birth favorite_color))

  (testing "with valid attributes"
    (is (= (get valid_person :last_name) last_name))
    (is (= (get valid_person :first_name) first_name))
    (is (= (get valid_person :gender) gender))
    (is (= (get valid_person :date_of_birth) date_of_birth))
    (is (= (get valid_person :favorite_color) favorite_color))))

;; Initialize Person record using constructor function
(testing "Construct a Person record"
  (def favorite_color "blue")
  (def date_of_birth "12/19/1975")
  (def gender "M")
  (def first_name "Mike")
  (def last_name "Hall")

  (def valid_person (make-person {
                                  :favorite_color favorite_color
                                  :date_of_birth date_of_birth
                                  :gender gender
                                  :first_name first_name
                                  :last_name last_name }))

  (testing "with valid attributes"
    (is (= (get valid_person :last_name) last_name))
    (is (= (get valid_person :first_name) first_name))
    (is (= (get valid_person :gender) gender))
    (is (= (get valid_person :favorite_color) favorite_color))
    (is (instance? org.joda.time.DateTime (get valid_person :date_of_birth)))
    (is (d/date? (get valid_person :date_of_birth)))))
