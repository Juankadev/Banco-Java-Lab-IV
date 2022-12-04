function validarDNI(dni){
	if(dni.length < 11){
		if(Integer.isNumber(dni)){
			return true;
		}
	}
	
	return false;
}