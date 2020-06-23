import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import classNames from 'classnames';
import { Link } from 'react-router-dom';
import { Button, Badge, Card, CardBody,CardFooter, CardHeader, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap';
import { rgbToHex } from '@coreui/coreui/dist/js/coreui-utilities'


class AccountMngList extends Component {
  constructor(props) {
    super(props);

    this.state = { 
      data : []
    };
  }

  componentDidMount() {
      this.renderData();
  }

  renderData(){
    axios.post('http://127.0.0.1:8082/service/api/users/list')
    .then( response => { 
        console.log(response.data); 
        this.setState({ data : response.data})
      } ) // SUCCESS
      .catch( response => {
      }
    );
  }

  handleClick(){
    this.props.history.push("/account-mng-form");
  };

  render() {

    function enabledRender(enable){
      if(enable){
        return "사용중";
      }else{
        return "사용중지";
      }
    }

    const getBadge = (status) => {
      return status === '1' ? 'success' : 'warning'
    }

    var rowRender = this.state.data.map(function(item) {
      console.log("item:"+item)
      const userLink = `/users/${item.username}`
      return (
        <tr key={item.username}>
          <th><Link to={userLink}>{item.username}</Link></th>
          <td>{item.authority}</td>
          <td>{item.reg_date}</td>
          <td>{item.upd_date}</td>
          <td><Badge color={getBadge(item.enabled)}>{enabledRender(item.enabled)}</Badge></td>
        </tr>
      );
    });

    return (
      <div className="animated fadeIn">
        <Row>
          <Col>
            <Card>
              <CardHeader>
                <i className="fa fa-align-justify"></i> 계정목록
              </CardHeader>
              <CardBody>
                <Table responsive hover>
                  <thead>
                  <tr>
                    <th>이름</th>
                    <th>권한</th>
                    <th>등록일</th>
                    <th>수정일</th>
                    <th>사용여부</th>
                  </tr>
                  </thead>
                  <tbody>
                    {rowRender}
                  </tbody>
                </Table>
              </CardBody>
              <CardFooter>
                <Button onClick={() => this.handleClick()}>등록</Button>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default AccountMngList;
