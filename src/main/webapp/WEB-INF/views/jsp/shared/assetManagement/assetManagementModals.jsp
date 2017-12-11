<%--Modal View--%>
<div class="modal fade" id="asset_view" tabindex="-1" role="dialog" aria-labelledby="asset_view">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">
                    <i class="fa fa-cube" aria-hidden="true"></i> Asset Information
                </h4>
            </div>


            <div class="modal-body">

                <div class="row">
                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Inventory number</span>
                                <label id="a_management_invNumber"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Asset name (Or)</span>
                                <label id="a_management_nameRu"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Asset name (Tr)</span>
                                <label id="a_management_nameEn"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Owner</span>
                                <label id="a_management_owner"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Location</span>
                                <label id="a_management_location"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Cost</span>
                                <label id="a_management_cost"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Registration date</span>
                                <label id="a_management_regDate"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="col-md-10 col-md-offset-1 asset_management_fg">
                            <div class="input-group">
                                <span class="input-group-addon">Registration info</span>
                                <label id="a_management_regInfo"></label>
                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <ul id="a_management_historyTable" class="list-group col-md-10 col-md-offset-1">
                            <label id="a_management_historyLabel">History</label>
                            <li class="list-group-item">Cras justo odio</li>
                            <li class="list-group-item">Dapibus ac facilisis in</li>
                            <li class="list-group-item">Morbi leo risus</li>
                            <li class="list-group-item">Porta ac consectetur ac</li>
                            <li class="list-group-item">Vestibulum at eros</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                    <button id="assetSaveBtn" class="btn btn-green" type="submit" name="submit">Save</button>
                    <button id="assetUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <%--Modal User Assets
    <div class="modal fade" id="asset_view" tabindex="-1" role="dialog" aria-labelledby="asset_view">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabel"></h4>
                </div>
                <form modelAttribute="assetVM" method="post" id="assetForm">
                    <div class="modal-body">
                        <input class="hidden" id="asset_id"/>


                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Inventory Number</span>
                            <input id="asset_inventoryNumber" class="form-control"/>
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Category</span>
                            <select id="asset_category_select"></select>
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Public</span>
                            <checkbox id="asset_public"/>
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Owner</span>
                            <div id="assetOwnerDiv">
                                <input id="asset_owner" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                        <button id="assetSaveBtn" class="btn btn-green" type="submit" name="submit">Save</button>
                        <button id="assetUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>--%>

    <script>
        function assetModal(id) {

            var modal = $('#asset_view');

            if (id != "") {

                modal.find("label").not('#a_management_historyLabel').empty();
                $('ul#a_management_historyTable li').remove();


                $.get("/AssetManagement/GetAssetByIDJson?inventNum=" + id, function (data, status) {

                    $('#a_management_invNumber').html(pad(data.inventNum, 9));
                    $('#a_management_nameRu').html(data.nameRu);
                    $('#a_management_nameEn').html(data.nameEn);
                    $('#a_management_owner').html(data.ownerFullName);
                    $('#a_management_location').html(data.location);
                    $('#a_management_cost').html(data.cost.toLocaleString() + ' UZS');
                    $('#a_management_regDate').html(data.regDate);
                    $('#a_management_regInfo').html(data.regInfo);
                    console.log(data)

                    if (data.assetHistoryVMList == "") {
                        $('ul#a_management_historyTable').append("<li class='list-group-item'>no records</li>");
                    } else {
                        $.each(data.assetHistoryVMList, function (item, value) {

                            $('ul#a_management_historyTable').append("<li class='list-group-item'>" +
                                "On " + value.regDate + " this asset was assigned from " + value.userNewId.fullName + " to " + value.userOldId.fullName
                                + "</li>");
                        });
                    }

                });

                modal.modal('show');
            }


            function pad(str, max) {
                return str.toString().length < max ? pad("0" + str, max) : str;
            }

        }

        function assetUser() {

        }
    </script>
