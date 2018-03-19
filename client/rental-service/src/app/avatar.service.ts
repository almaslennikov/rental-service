import { Injectable } from '@angular/core';

@Injectable()
export class AvatarService {

  avatars = [
    'https://semantic-ui.com/images/avatar/large/elliot.jpg',
    'https://semantic-ui.com/images/avatar2/large/matthew.png',
    'https://semantic-ui.com/images/avatar/large/daniel.jpg',
    'https://semantic-ui.com/images/avatar/large/steve.jpg',
    'http://vm19755.hv8.ru/mypin/bower_components/semantic/examples/assets/images/avatar/tom.jpg',
    'https://thefancy-media-ec3.thefancy.com/UserImages/original/forsale_9dcbce2a18d8.jpg',
    'http://semantic-ui.cn/images/avatar/large/chris.jpg',
    'http://themes.minispace.org/kentut/assets/img/avatar/avatar-4.jpg'
  ];

  constructor() { }

  getAvatarUrl(id: number) {
    return this.avatars[id % this.avatars.length];
  }
}
