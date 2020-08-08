(set-option :timeout 5000)
(declare-fun tvw_a () Int)
<<<<<<< HEAD
(declare-fun tvw_b () Int)
(assert (not  (<  tvw_a   0 ) ) )
(assert (<  tvw_b   0 ) )
(assert (not  (=  tvw_a   0 ) ) )
(assert (=  (+  (*  (- 1)   tvw_b )   0 )   0 ) )
=======
(assert (<  tvw_a   5 ) )
(assert (=  tvw_a   5 ) )
>>>>>>> 1107e24
(check-sat)(get-model)