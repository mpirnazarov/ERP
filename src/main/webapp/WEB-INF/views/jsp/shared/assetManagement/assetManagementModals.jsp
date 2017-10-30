<%--Modal View--%>
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
    function assetModal() {
        $('#asset_view').modal('show');
        console.log("yep")
    }

    function assetUser() {

    }
</script>
