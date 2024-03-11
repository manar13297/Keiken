# Chalenge 1

Extract a component

---
### Solution:
`Apps.js`:
```javascript
import { getImageUrl } from './utils.js';
import Profile from './utils.js'
export default function Gallery() {
  return (
    <div>
      <h1>Notable Scientists</h1>
      <Profile
        scientist =  {{
          name : "Maria Skłodowska-Curie",
          profession : "physicist and chemist",
          awards : "(Nobel Prize in Physics, Nobel Prize in Chemistry, Davy Medal, Matteucci Medal)",
          discovered : "polonium (chemical element)"
        }}
        img =  {{
          imgId : "szV5sdG"
        }}

        />
      <Profile
        scientist =  {{
          name : "Katsuko Saruhashi",
          profession : "geochemist",
          awards : "(Miyake Prize for geochemistry, Tanaka Prize)",
          discovered : "a method for measuring carbon dioxide in seawater"
        }}
        img =  {{
          imgId : "YfeOqp2"
        }}

        />

    </div>
  );
}

```
`utils.js`:
```javascript
export function getImageUrl(imageId, size = 's') {
    return (
        'https://i.imgur.com/' +
        imageId +
        size +
        '.jpg'
    );
}


export default function Profile({scientist, img}){
    const {name ,profession , awards, discovered} = scientist
    const {className="avatar" ,imgId , width=70, height=70} = img

    return(
        <section className="profile">
            <h2>{name}</h2>
            <img
                className={className}
                src={getImageUrl(imgId)}
                alt={name}
                width={width}
                height={height}
            />
            <ul>
                <li>
                    <b>Profession: </b>
                    {profession}
                </li>
                <li>
                    <b>Awards: 4 </b>
                    {awards}
                </li>
                <li>
                    <b>Discovered: </b>
                    {discovered}
                </li>
            </ul>
        </section>
    );
}


```

# Chalenge 2
Adjust the image size based on a prop

---

`Apps.js`:
```javascript
import { getImageUrl } from './utils.js';

function Avatar({ person, size }) {
    const thumbnailSize = size < 90 ? 's' : 'b';
    return (
        <img
            className="avatar"
            src={getImageUrl(person, thumbnailSize)}
            alt={person.name}
            width={size}
            height={size}
        />
    );
}

export default function Profile() {
    return (
        <Avatar
            size={40}
            person={{
                name: 'Gregorio Y. Zara',
                imageId: '7vQD0fP'
            }}
        />
    );
}

```

# Challenge 3
Passing JSX in a children prop

---
```javascript
export function Card({children}){
    return(
        <div className="card">
            <div className="card-content">
                {children}
            </div>
        </div>
    );
}

export default function Profile() {
    return (
        <div>
            <Card>
                <h1>Photo</h1>
                <img
                    className="avatar"
                    src="https://i.imgur.com/OKS67lhm.jpg"
                    alt="Aklilu Lemma"
                    width={70}
                    height={70}
                />
            </Card>
            <Card>
                <h1>About</h1>
                <p>Aklilu Lemma was a distinguished Ethiopian scientist who discovered a natural treatment to schistosomiasis.</p>
            </Card>
        </div>
    );
}
```