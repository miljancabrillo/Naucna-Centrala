import { Review } from './Review';

export class FormField{
    id : string;
	type : string;
	label : string;
	value : any;
	selectValues : Map<string,string>;	
	fileName : string;
	linkText : string; 
	multiselectValues : string[];
	reviews : Review[];
	linkUrl : string;
}