# Responding to Events
- Challenge 1 of 2: Fix an event handler 

````javascript
export default function LightSwitch() {
  function handleClick() {
    let bodyStyle = document.body.style;
    if (bodyStyle.backgroundColor === 'black') {
      bodyStyle.backgroundColor = 'white';
    } else {
      bodyStyle.backgroundColor = 'black';
    }
  }

  return (
      //The problem is here
      // We don't call event handlers, we pass them
    <button onClick={handleClick()}>
      Toggle the lights
    </button>
  );
}

````

Solution:
````javascript
export default function LightSwitch() {
  function handleClick() {
    let bodyStyle = document.body.style;
    if (bodyStyle.backgroundColor === 'black') {
      bodyStyle.backgroundColor = 'white';
    } else {
      bodyStyle.backgroundColor = 'black';
    }
  }

  return (
    <button onClick={handleClick}>
      Toggle the lights
    </button>
  );
}

````

- Challenge 2 of 2: Wire up the events

````javascript
export default function ColorSwitch({
      onChangeColor
    }) {
    return (
        <button>
            Change color
        </button>
    );
}

````
Solution:
- Add an onClick event to the button
- Stop propagation because the button click event might be propagating up to a parent component that
  is listening for click events to increment the counter
````javascript
export default function ColorSwitch({onChangeColor}){
    const handelClick = (e)=> {
        e.stopPropagation();
        onChangeColor();
    }
    return (
        <button onClick={handelClick}>
            Change color
        </button>
    );
}
````

# State: A Component's Memory
- Solution of Challenge 1 of 4: Complete the gallery 
````javascript
import { useState } from 'react';
import { sculptureList } from './data.js';

export default function Gallery() {
    const [index, setIndex] = useState(0);
    const [showMore, setShowMore] = useState(false);

    function handleNextClick() {
        if (index < sculptureList.length - 1) {
            setIndex(index + 1);
        }
    }

    function handlePreviousClick() {
        if (index > 0) {
            setIndex(index - 1);
        }
    }

    function handleMoreClick() {
        setShowMore(!showMore);
    }

    let sculpture = sculptureList[index];
    return (
        <>
            <button onClick={handlePreviousClick} disabled={index === 0}>
                Previous
            </button>
            <button onClick={handleNextClick} disabled={index === sculptureList.length - 1}>
                Next
            </button>
            <h2>
                <i>{sculpture.name} </i>
                by {sculpture.artist}
            </h2>
            <h3>
                ({index + 1} of {sculptureList.length})
            </h3>
            <button onClick={handleMoreClick}>
                {showMore ? 'Hide' : 'Show'} details
            </button>
            {showMore && <p>{sculpture.description}</p>}
            <img
                src={sculpture.url}
                alt={sculpture.alt}
            />
        </>
    );
}

````
- Solution of Challenge 2 of 4: Fix stuck form inputs 
> In React, regular variables don't preserve their values across re-renders
> 
> We should use React's useState hook to manage the firstName and lastName variables as state variables.
````javascript
import {useState} from 'react';
export default function Form() {
  const [firstName , setFirstName] = useState('');
  const [lastName , setLastName] = useState('');

  function handleFirstNameChange(e) {
    setFirstName(e.target.value);
  }

  function handleLastNameChange(e) {
    setLastName(e.target.value);
  }

  function handleReset() {
    setFirstName('');
    setLastName('');
  }

  return (
    <form onSubmit={e => e.preventDefault()}>
      <input
        placeholder="First name"
        value={firstName}
        onChange={handleFirstNameChange}
      />
      <input
        placeholder="Last name"
        value={lastName}
        onChange={handleLastNameChange}
      />
      <h1>Hi, {firstName} {lastName}</h1>
      <button onClick={handleReset}>Reset</button>
    </form>
  );
}

````
- Solution of Challenge 3 of 4: Fix a crash  
> move useState hook for message to the top level of the function
````javascript
import { useState } from 'react';

export default function FeedbackForm() {
    const [message, setMessage] = useState('');
    const [isSent, setIsSent] = useState(false);

    if (isSent) {
        return <h1>Thank you!</h1>;
    } else {
        return (
            <form onSubmit={e => {
                e.preventDefault();
                alert(`Sending: "${message}"`);
                setIsSent(true);
            }}>
                <textarea
                    placeholder="Message"
                    value={message}
                    onChange={e => setMessage(e.target.value)}
                />
                <br />
                <button type="submit">Send</button>
            </form>
        );
    }
}

````
- Solution of Challenge 4 of 4: Remove unnecessary state
> The state variable was unnecessary:
> 
> 1. both the prompt('What is your name?') call and the alert to display the greeting were executed in the same event handler function, without waiting for the component to re-render in between.
> 
> 2. Since the alert is shown right after setting the name, it accesses the current value of the name state variable, which hasn't been updated yet and is still an empty string.
````javascript
import { useState } from 'react';

export default function FeedbackForm() {

    function handleClick() {
        const name = prompt('What is your name?');
        alert(`Hello, ${name}!`);
    }

    return (
        <button onClick={handleClick}>
            Greet
        </button>
    );
}

````
# State as a snapshot
Challenge: implement a traffic light

````javascript
import { useState } from 'react';

export default function TrafficLight() {
  const [walk, setWalk] = useState(true);

  function handleClick() {
    setWalk(!walk);
    alert(walk ? 'Stop is next' : 'Walk is next');
  }

  return (
    <>
      <button onClick={handleClick}>
        Change to {walk ? 'Stop' : 'Walk'}
      </button>
      <h1 style={{
        color: walk ? 'darkgreen' : 'darkred'
      }}>
        {walk ? 'Walk' : 'Stop'}
      </h1>
    </>
  );
}

````

>State updates in React are asynchronous;
> 
> Since the state update hasn't been applied yet (regardless of whether the alert is called before or after setWalk), the value of walk accessed by the alert function is still the state's value before the update.
> 
> The actual state update to walk will only take effect after the component re-renders, which happens after the handleClick function has completed execution.

# Queueing a Series of State Updates
- Soluton of Challenge 1 of 2: Fix a request counter
> We will use updater functions instead, to ensure that the state is updated based on the most recent previous state,
````javascript
import { useState } from 'react';

export default function RequestTracker() {
  const [pending, setPending] = useState(0);
  const [completed, setCompleted] = useState(0);

  async function handleClick() {
    setPending((pending)=>pending + 1);
    await delay(3000);
    setPending((pending)=>pending - 1);
    setCompleted((completed)=>completed + 1);
  }

  return (
    <>
      <h3>
        Pending: {pending}
      </h3>
      <h3>
        Completed: {completed}
      </h3>
      <button onClick={handleClick}>
        Buy     
      </button>
    </>
  );
}

function delay(ms) {
  return new Promise(resolve => {
    setTimeout(resolve, ms);
  });
}

````

- Solution of Challenge 2 of 2: Implement the state queue yourself 
````javascript
export function getFinalState(baseState, queue) {
  let finalState = baseState;
  for (let update of queue) {
    if (typeof update === 'function') {
      finalState =update(finalState);
      } else {
        finalState = update
      }
  }

  return finalState;
}

````
# Updating objects in state
- Solution of Challenge 1 of 3: Fix incorrect state updates 
> We don't mutate objects, because mutating them will not trigger renders.
> 
> Instead of mutating an object, we create a new version of it
````javascript
import { useState } from 'react';

export default function Scoreboard() {
  const [player, setPlayer] = useState({
    firstName: 'Ranjani',
    lastName: 'Shettar',
    score: 10,
  });

  function handlePlusClick() {
    setPlayer(
      {
        ...player,
        score: player.score+1,
      }
    )
  }

  function handleFirstNameChange(e) {
    setPlayer({
      ...player,
      firstName: e.target.value,
    });
  }

  function handleLastNameChange(e) {
    setPlayer({
      ...player,
      lastName: e.target.value
    });
  }

  return (
    <>
      <label>
        Score: <b>{player.score}</b>
        {' '}
        <button onClick={handlePlusClick}>
          +1
        </button>
      </label>
      <label>
        First name:
        <input
          value={player.firstName}
          onChange={handleFirstNameChange}
        />
      </label>
      <label>
        Last name:
        <input
          value={player.lastName}
          onChange={handleLastNameChange}
        />
      </label>
    </>
  );
}

````
- Solution of Challenge 2 of 3: Find and fix the mutation
> Same idea; instead of mutating an object, we create a new version of it
````javascript
import { useState } from 'react';
import Background from './Background.js';
import Box from './Box.js';

const initialPosition = {
    x: 0,
    y: 0
};

export default function Canvas() {
    const [shape, setShape] = useState({
        color: 'orange',
        position: initialPosition
    });

    function handleMove(dx, dy) {
        const newPosition = {
            x : shape.position.x + dx,
            y : shape.position.y +dy,
        }
        setShape(
            {
                ...shape,
                position: newPosition,
            }
        )

    }

    function handleColorChange(e) {
        setShape({
            ...shape,
            color: e.target.value
        });
    }

    return (
        <>
            <select
                value={shape.color}
                onChange={handleColorChange}
            >
                <option value="orange">orange</option>
                <option value="lightpink">lightpink</option>
                <option value="aliceblue">aliceblue</option>
            </select>
            <Background
                position={initialPosition}
            />
            <Box
                color={shape.color}
                position={shape.position}
                onMove={handleMove}
            >
                Drag me!
            </Box>
        </>
    );
}


````
- Solution of Challenge 3 of 3: Update an object with Immer
> Immer provide a draft object. It called a Proxy, that records what we do with it.
````javascript
import { useState } from 'react';
import { useImmer } from 'use-immer';
import Background from './Background.js';
import Box from './Box.js';

const initialPosition = {
  x: 0,
  y: 0
};

export default function Canvas() {
  const [shape, setShape] = useImmer({
    color: 'orange',
    position: initialPosition
  });

  function handleMove(dx, dy) {
    setShape((d)=>{
      d.position.x+= dx;
      d.position.y += dy;
    })
  }

  function handleColorChange(e) {
    setShape((d)=>{
      d.color =  e.target.value
    });
  }

  return (
    <>
      <select
        value={shape.color}
        onChange={handleColorChange}
      >
        <option value="orange">orange</option>
        <option value="lightpink">lightpink</option>
        <option value="aliceblue">aliceblue</option>
      </select>
      <Background
        position={initialPosition}
      />
      <Box
        color={shape.color}
        position={shape.position}
        onMove={handleMove}
      >
        Drag me!
      </Box>
    </>
  );
}

````
# Updating Arrays in State
- Solution of Challenge 1 of 4: Update an item in the shopping cart 
````javascript
import { useState } from 'react';

const initialProducts = [{
  id: 0,
  name: 'Baklava',
  count: 1,
}, {
  id: 1,
  name: 'Cheese',
  count: 5,
}, {
  id: 2,
  name: 'Spaghetti',
  count: 2,
}];

export default function ShoppingCart() {
  const [
    products,
    setProducts
  ] = useState(initialProducts)

  function handleIncreaseClick(productId) {
    setProducts(
      products.map((product)=>{
      if(productId == product.id) return {...product, count: product.count+1}
      else return {...product}
    })
    )
  }

  return (
    <ul>
      {products.map(product => (
        <li key={product.id}>
          {product.name}
          {' '}
          (<b>{product.count}</b>)
          <button onClick={() => {
            handleIncreaseClick(product.id);
          }}>
            +
          </button>
        </li>
      ))}
    </ul>
  );
}

````

- Solution of Challenge 2 of 4: Remove an item from the shopping cart
````javascript
import { useState } from 'react';

const initialProducts = [{
    id: 0,
    name: 'Baklava',
    count: 1,
}, {
    id: 1,
    name: 'Cheese',
    count: 5,
}, {
    id: 2,
    name: 'Spaghetti',
    count: 2,
}];

export default function ShoppingCart() {
    const [
        products,
        setProducts
    ] = useState(initialProducts)

    function handleIncreaseClick(productId) {
        setProducts(products.map(product => {
            if (product.id === productId) {
                return {
                    ...product,
                    count: product.count + 1
                };
            } else {
                return product;
            }
        }))
    }

    function handleDecreaseClick(productId) {
        const arr =
            products.map(product => {
                if (product.id === productId) {
                    return {
                        ...product,
                        count: product.count - 1
                    };
                } else {
                    return product;
                }
            }).filter(product=>product.count>0);

        setProducts(arr);

    }

    return (
        <ul>
            {products.map(product => (
                <li key={product.id}>
                    {product.name}
                    {' '}
                    (<b>{product.count}</b>)
                    <button onClick={() => {
                        handleIncreaseClick(product.id);
                    }}>
                        +
                    </button>
                    <button onClick = {()=>
                        handleDecreaseClick(product.id)}>
                        –
                    </button>
                </li>
            ))}
        </ul>
    );
}

````
- Solution of Challenge 3 of 4: Fix the mutations using non-mutative methods
````javascript
import { useState } from 'react';
import AddTodo from './AddTodo.js';
import TaskList from './TaskList.js';

let nextId = 3;
const initialTodos = [
    { id: 0, title: 'Buy milk', done: true },
    { id: 1, title: 'Eat tacos', done: false },
    { id: 2, title: 'Brew tea', done: false },
];

export default function TaskApp() {
    const [todos, setTodos] = useState(
        initialTodos
    );

    function handleAddTodo(title) {
        setTodos([
            ...todos,
            {
                id: nextId++,
                title: title,
                done: false
            }
        ]);
    }

    function handleChangeTodo(nextTodo) {
        setTodos(todos.map(t => {
            if (t.id === nextTodo.id) {
                return nextTodo;
            } else {
                return t;
            }
        }));
    }

    function handleDeleteTodo(todoId) {
        setTodos(
            todos.filter(t => t.id !== todoId)
        );
    }

    return (
        <>
            <AddTodo
                onAddTodo={handleAddTodo}
            />
            <TaskList
                todos={todos}
                onChangeTodo={handleChangeTodo}
                onDeleteTodo={handleDeleteTodo}
            />
        </>
    );
}

````
- Solution of Challenge 4 of 4: Fix the mutations using Immer 
````javascript
import { useState } from 'react';
import { useImmer } from 'use-immer';
import AddTodo from './AddTodo.js';
import TaskList from './TaskList.js';

let nextId = 3;
const initialTodos = [
  { id: 0, title: 'Buy milk', done: true },
  { id: 1, title: 'Eat tacos', done: false },
  { id: 2, title: 'Brew tea', done: false },
];

export default function TaskApp() {
  const [todos, updateTodos] = useImmer(
    initialTodos
  );

  function handleAddTodo(title) {
    updateTodos(draft => {
      draft.push({
        id: nextId++,
        title: title,
        done: false
      });
    });
  }

  function handleChangeTodo(nextTodo) {
    updateTodos(draft => {
      const todo = draft.find(t =>
        t.id === nextTodo.id
      );
      todo.title = nextTodo.title;
      todo.done = nextTodo.done;
    });
  }

  function handleDeleteTodo(todoId) {
    updateTodos(draft => {
      const index = draft.findIndex(t =>
        t.id === todoId
      );
      draft.splice(index, 1);
    });
  }

  return (
    <>
      <AddTodo
        onAddTodo={handleAddTodo}
      />
      <TaskList
        todos={todos}
        onChangeTodo={handleChangeTodo}
        onDeleteTodo={handleDeleteTodo}
      />
    </>
  );
}

````

