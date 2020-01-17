import { ScientificArea } from './ScientificArea';
import { User } from './User';
import { ScientificAreaEditorPair } from './ScientificAreaEditorPair';

export class Magazine{
    
	name : string;
	ISSN : number;
	scientificAreas : ScientificArea[];
	openAccess : boolean = false;
	cheifEditor : User;
	scientificAreaEditorList : ScientificAreaEditorPair[];
	reviewers : User[];
	adminMessage : string;
	reviewed : boolean;
}