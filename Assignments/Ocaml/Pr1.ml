(* C:\Users\Samsung\Desktop\Pr1.ml *)

let hd a:int=
	match a with
	[]->raise(Failure "hd:empty list")
	|hd::tl->hd;;

let tl a:(int list)
	=match a with
	[]->raise(Failure "tl:empty list")
	|hd::tl->tl;;

let rec zipper(x,y)=
	match (x,y) with
	([],[])->[]
	|(x,[])-> x
	|([],y)->y
	|(x,y)->[hd x]@zipper(y,(tl x));;