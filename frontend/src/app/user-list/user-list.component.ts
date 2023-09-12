import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit
{
  users: User[];

  constructor(private userService: UserService) {}

  ngOnInit(): void 
  {
    this.getUsers();
  }

  private getUsers(): void
  {
    this.userService.getUserList().subscribe(user => 
    {
      this.users = user;
    });
  }
}
