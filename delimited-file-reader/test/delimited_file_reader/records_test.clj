(ns delimited-file-reader.records-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.records :refer :all])
  (:import [delimited_file_reader.records Person]))

(def valid-last_name "Hall")
(def valid-first_name "Mike")
(def valid-gender"M")
(def valid-date_of_birth "12/19/1975")
(def valid-favorite_color "blue")

;; Construct a raw defrecord
(def valid-person (Person. valid-last_name
                           valid-first_name
                           valid-gender
                           valid-date_of_birth
                           valid-favorite_color))
(deftest valid-person-test
  (testing "New Person record can be directly constructed"
    (is (= (get valid-person :last_name) valid-last_name))
    (is (= (get valid-person :first_name) valid-first_name))
    (is (= (get valid-person :gender) valid-gender))
    (is (= (get valid-person :date_of_birth) valid-date_of_birth))
    (is (= (get valid-person :favorite_color) valid-favorite_color))))

;; Construct Person with valid ordinal arguments
(def make-valid-person-with-ordinal-arguments
  (make-person valid-last_name
               valid-first_name
               valid-gender
               valid-date_of_birth
               valid-favorite_color))
(deftest construct-person-with-ordinal-arguments-test
  (testing "A Person can be constructed with valid ordinal arguments"
    (is (= (get make-valid-person-with-ordinal-arguments :last_name) valid-last_name))
    (is (= (get make-valid-person-with-ordinal-arguments :first_name) valid-first_name))
    (is (= (get make-valid-person-with-ordinal-arguments :gender) valid-gender))
    (is (= (get make-valid-person-with-ordinal-arguments :date_of_birth) valid-date_of_birth))
    (is (= (get make-valid-person-with-ordinal-arguments :favorite_color) valid-favorite_color))))

;; Construct Person with valid keyword arguments
(def make-valid-person-with-keyword-arguments
  (make-person {
                :favorite_color valid-favorite_color
                :date_of_birth valid-date_of_birth
                :gender valid-gender
                :first_name valid-first_name
                :last_name valid-last_name }))
(deftest construct-person-with-keyword-arguments-test
  (testing "A Person can be constructed with valid keyword arguments"
    (is (= (get make-valid-person-with-keyword-arguments :last_name) valid-last_name))
    (is (= (get make-valid-person-with-keyword-arguments :first_name) valid-first_name))
    (is (= (get make-valid-person-with-keyword-arguments :gender) valid-gender))
    (is (= (get make-valid-person-with-keyword-arguments :date_of_birth) valid-date_of_birth))
    (is (= (get make-valid-person-with-keyword-arguments :favorite_color) valid-favorite_color))))
