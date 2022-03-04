(defn fatorial [x]
    (if (<= x 1) 1 
    (* x (fatorial (- x 1))))
)

(println (fatorial 5))