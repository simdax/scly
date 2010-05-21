
+ SequenceableCollection {


	// Usage:
	// [1, 2, 1, 1, [2, [1, 1, 1, 1,1]], 3, 1, 1].rotateTree(2)
	// -> [ 1, 1, 1, 2, [ 1, [ 1, 2, 1, 1, 1 ] ], 1, 1, 3 ]

	rotateTree { arg nRotations = 1 ;
		var newTree;
		newTree = this.flat.rotate(nRotations).bubble;
		^newTree.reshapeLike(this);
		
	}

	asLisp { 
		// output a 'string' with lisplike syntax
		var lispLike = "( ";
		this.do({ arg item;
			if( item.isNumber,
				{lispLike = lispLike ++ item.asString ++ " "},
				{lispLike = lispLike ++ item.asLisp ++ " "}
			)
		});
		^(lispLike ++ ")") 
	}
	
	hasArray { 
		^this.any{|i| i.isKindOf(SequenceableCollection)}
	}
	

	extractFlat {
		var out; 
		out = Array.new;
		this.do({|i|
			if(
				i.isArray,
				{out = out.add(i[0])},
				{out = out.add(i)}
			)
		});
		^out;
		}

	otherSum {
		var otherSum=0;
		this.do({|i|
			
			if(
				i.isArray == false,
				{otherSum = otherSum + i},
				{otherSum = otherSum + i[0]}
			)
			
		});
		^otherSum;
	}
	
	
}