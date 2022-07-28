import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import ListContactComponent from './components/ListContactComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateContactComponent from './components/CreateContactComponent';
import ShowContactComponent from './components/ShowContactComponent';
import UpdateContactComponent from './components/UpdateContactComponent';
import PageNotFound from './components/PageNotFound'

function App() {
  return (
    
      <Router>
        <HeaderComponent/>
          <div className="container">
            <Routes>
              <Route  path="/" exact element = {<ListContactComponent/>}/>
              <Route  path="/add-contact"  element = {<CreateContactComponent/>}/>
              <Route  path="/show/contact/:id" element = {<ShowContactComponent  />}/>
              <Route  path="/contact/update/:id" element = {<UpdateContactComponent />}/>
              <Route  path="*" element = {<PageNotFound/>}/>
            </Routes>
          </div>
        <FooterComponent/>
      </Router>
    
  );
}

export default App;
