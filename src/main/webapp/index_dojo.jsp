<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 24.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <style type="text/css">
      @import "//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dijit/themes/claro/claro.css";
      @import "//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dojox/grid/resources/claroGrid.css";
    </style>
    <title>ECM</title>
    <script>dojoConfig = {parseOnLoad: true}</script>
    <script src="//ajax.googleapis.com/ajax/libs/dojo/1.13.0/dojo/dojo.js"></script>
    <script type="text/javascript">
        require(
            [
                "dojo/json",
                "dojox/grid/DataGrid",
                "dojo/request",
                "dojo/data/ObjectStore",
                "dojo/store/Memory",
                "dijit/dijit",
                "dojo/ready",
                "dijit/layout/BorderContainer",
                "dijit/layout/ContentPane",
                "dijit/layout/TabContainer",
                "dijit/form/Form",
                "dijit/form/Button",
                "dijit/form/ValidationTextBox",
                "dijit/form/DateTextBox"],
            function(json, DataGrid, request, ObjectStore, Memory, dijit, ready, BorderContainer, ContentPane, TabContainer) {
                ready(function(){
                    var grid = dijit.byId("gridId");
                    var tabContainer = dijit.byId("tabContainer");
                    request("ecm/employees/persons").then(function(res){
                        var store = new Memory({idProperty: "id", data: json.parse(res)});
                        var objStore = new ObjectStore({
                            objectStore: store
                        });
                        grid.setStore(objStore);
                    });
                    grid.on("click",function(e){
                        var index = e.rowIndex;
                        var item = grid.getItem(index);
                        request("ecm/employees/"+item.id).then(function(response){
                            var store = new Memory({idProperty: "id", data: json.parse(response)});
                            var objStore = new ObjectStore({objectStore: store});
                            var cp = new ContentPane({
                              title: item.lastName + " " + item.firstName,
                              content: "<div id='documentGrid"+item.id+"' style='height:70%;'></div>",
                              closable: true
                            });
                            tabContainer.addChild(cp);
                            tabContainer.selectChild(cp);
                            var layout = [[
                                {'name': 'ID', 'field': 'id', 'width': '100px'},
                                {'name': 'Тип', 'field': 'storeName', 'width': '100px'},
                                {'name': 'Название', 'field': 'name', 'width': '200px'},
                                {'name': 'Исполнитель', 'field': 'executor', 'width': '200px', formatter: function(data, rowIndex, cell){
                                      if(data && data.firstName){
                                          return data.firstName;
                                      }
                                      return "-empty-";
                                    }}
                            ]];
                            var docGrid = new DataGrid({
                                id:"docGrid"+item.id,
                                store: objStore,
                                structure: layout
                            });
                            docGrid.placeAt("documentGrid"+item.id);
                            docGrid.startup();
                        });
                    });
                });
            });
    </script>
  </head>
  <body class="claro" style="width:100%; height: 100%; margin: 0; overflow: hidden;">

  <div data-dojo-type="dijit/layout/BorderContainer" style="width:100%; height: 100%" data-dojo-props="design:'sidebar', gutters:true, liveSplitters:true" id="borderContainer">
    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'leading'" style="width: 100px;">${title[0].firstName}</div>

    <div data-dojo-type="dijit/layout/TabContainer" id="tabContainer" data-dojo-props="region:'center', tabStrip:true">
        <div data-dojo-type="dijit/layout/ContentPane" title="Person Table" selected="true">
          <table id="gridId" data-dojo-type="dojox/grid/DataGrid" style="height: 70%;">
            <thead>
              <tr>
                <th field="id">ID</th>
                <th field="firstName" width="10em">FirstName</th>
                <th field="middleName" width="10em">MiddleName</th>
                <th field="lastName" width="10em">LastName</th>
              </tr>
            </thead>
          </table>
          <button data-dojo-type="dijit/form/Button" type="button" onClick="console.log(myForm.getValues())">Get Values from form!</button>
          <button data-dojo-type="dijit/form/Button" type="submit" name="submitButton" value="Submit">Submit</button>
          <button data-dojo-type="dijit/form/Button" type="reset">Reset</button>
        </div>
      <div data-dojo-type="dijit/layout/ContentPane" title="Creating test" selected="true">
        <div data-dojo-type="dijit/form/Form" id="myForm" data-dojo-id="myForm"
             encType="multipart/form-data" action="" method="">
          <script type="dojo/on" data-dojo-event="reset">
        return confirm('Press OK to reset widget values');
    </script>

          <script type="dojo/on" data-dojo-event="submit">
              if(this.validate()){
                  return confirm('Form is valid, press OK to submit');
              }else{
                  alert('Form contains invalid data.  Please correct first');
                  return false;
              }
              return true;
          </script>
          <table style="border: 1px solid #9f9f9f;" cellspacing="10">
            <tr>
              <td>
                <label for="name">Name:</label>
              </td>
              <td>
                <input type="text" id="name" name="name" required="true" data-dojo-type="dijit/form/ValidationTextBox"/>
              </td>
            </tr>
            <tr>
              <td>
                <label for="dob">Date of birth:</label>
              </td>
              <td>
                <input type="text" id="dob" name="dob" data-dojo-type="dijit/form/DateTextBox"/>
              </td>
            </tr>
          </table>

          <button data-dojo-type="dijit/form/Button" type="button" onClick="console.log(myForm.getValues())">Get Values from form!</button>
          <button data-dojo-type="dijit/form/Button" type="submit" name="submitButton" value="Submit">Submit</button>
          <button data-dojo-type="dijit/form/Button" type="reset">Reset</button>
        </div>
      </div>
    </div>
  </div>

  </body>
</html>
