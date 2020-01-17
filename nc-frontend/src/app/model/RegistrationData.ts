import { ScientificArea } from './ScientificArea';

export class RegistrationData{
    name : string = "";
    surname : string = "";
    city : string = "";
    country : string= "";
    title : string= "";
    email : string= "";
    scientificAreas : ScientificArea[];
    username : string= "";
    password : string= "";
    reviewerCandidate : boolean;
    errorMessage : string = "";
}