(set-option :timeout 5000)
(declare-fun tvw_x () Real)
(assert (not  (>  tvw_x   1 ) ) )
(check-sat)(get-model)