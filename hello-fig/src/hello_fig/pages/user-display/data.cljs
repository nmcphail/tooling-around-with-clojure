(ns hello-fig.pages.user-display.data
  (:require 
                          ))

(def user (atom  [
                  {:field-name "Firstname" :field-value "Neil"}
                  {:field-name "Lastname" :field-value "McPhail"}
                  {:field-name "Age" :field-value 24 }
                  {:field-name "" :field-value "Neil"}
                  {:field-name "Another Field" :field-value "Another Value"}
                  {:field-name "Floating point field" :field-value "1.2345"}
                  {:field-name "Field with a long value" :field-value "A very long value indeed, would be good if it wrapped on multiple linesl"}
                  {:field-name "Firstname" :field-value "Neil"}
                  {:field-name "Firstname" :field-value "Neil"}
                  ]))


