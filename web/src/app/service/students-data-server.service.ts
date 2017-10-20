import {Injectable} from '@angular/core';
import {Student} from '../students/student';
import {Http, RequestOptions, Response, Headers} from '@angular/http';
import {StudentsDataService} from './students-data.service';
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/mergeMap";

@Injectable()
export class StudentsDataServerService {
  constructor(private http: Http) {

  }

  getStudentsData() {
    let studentArray: Student[];
    return this.http.get('http://localhost:8080/student')
      .map(res => res.json());
  }

  getStudent(id: number) {
    let student:Student;
    return this.http.get('http://localhost:8080/student/'+id)
      .map((res:Response) => {
        if (res){
          if (res.status === 2000){
            return res.json();
          }
          if (res.status === 204){
            return null;
          }
        }
      });
  }

  addStudent(student: Student,file:any) :Observable<Student>{
    const formData = new FormData();
    let fileName: string;
    formData.append('file',file);
    return this.http.post('http://localhost:8080/upload',formData)
      .flatMap(filename => {
        student.image = filename.text();
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers, method: 'post'});
        let body = JSON.stringify(student);
        return this.http.post('http://localhost:8080/student', body, options).map(res => {
          return res.json()
        })
      })
  }
}
