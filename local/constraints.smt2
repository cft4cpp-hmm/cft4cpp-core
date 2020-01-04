(set-option :timeout 5000)
(declare-fun tvw_x () Int)
(assert (and  (>  tvw_x   0 )   (<  tvw_x   2 ) ) )
(check-sat)(get-model)
