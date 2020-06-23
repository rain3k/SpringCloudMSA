import React, { Component } from 'react';
import axios from 'axios';
import { Button, Card, CardBody, CardFooter, Col, Container, Form, Label,FormGroup,Input, InputGroup, InputGroupAddon, InputGroupText, Row } from 'reactstrap';

class OAuthTestAuthorize extends Component {
  constructor(props) {
    super(props);

    this.state = { 
    };
  }

  onChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }

  handleClick = (e) => {
    axios.post('http://127.0.0.1:8082/oauth/authorize',this.state)
    .then( response => { 
        console.log(response.data); 
      } ) // SUCCESS
      .catch( response => {
      }
    );
  }

  render() {
    return (
      <div className="app flex-row align-items-center">
        <Container>
          <Row className="justify-content-center">
            <Col md="9" lg="7" xl="6">
              <Card className="mx-4">
                <Form>
                  <input name="user_oauth_approval" value="true" type="hidden" />
                  <CardBody className="p-4">
                    <h1>OAuth 권한 승인</h1>
                    <p className="text-muted">권한을 승인하시겠습니까?</p>
                    <FormGroup  check inline className="radio">
                      <Input className="form-check-input" type="radio" id="radio1" name="scope.read_profile" value="true" />
                      <Label check className="form-check-label" htmlFor="radio1">승인</Label>
                    </FormGroup>
                    <FormGroup  check inline className="radio">
                      <Input className="form-check-input" type="radio" id="radio2" name="scope.read_profile" value="false" />
                      <Label check className="form-check-label" htmlFor="radio2">거부</Label>
                    </FormGroup>
                  </CardBody>
                  <CardFooter>
                    <Button color="success" block onClick={() => this.handleClick()}>승인</Button>
                  </CardFooter>
                </Form>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default OAuthTestAuthorize;
