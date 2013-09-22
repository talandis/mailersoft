<?php
	
	require_once dirname(__FILE__).'/ML_Rest_Base.php';
	
	class ML_Rest extends ML_Rest_Base
	{
		var $name = '';

		var $id = null;

		function __construct( $api_key )
		{	
			parent::__construct();

			$this->apiKey = $api_key;

			$this->path = $this->url . $this->name . '/';
		}

		function setId( $id )
		{
			$this->id = $id;

			if ( $this->id )
				$this->path = $this->url . $this->name . '/' . $id . '/';
			else
				$this->path = $this->url . $this->name . '/';

			return $this;
		}

		function getAll( )
		{
			return $this->execute( 'GET' );
		}

		function get( $data )
		{
			if (!$this->id)
				throw new InvalidArgumentException('ID is not set.');

			return $this->execute( 'GET' );
		}

		function add( $data )
		{
			return $this->execute( 'POST', $data );
		}

		function put( $data = null)
		{
			return $this->execute( 'PUT', $data );
		}

		function remove( $data )
		{
			return $this->execute( 'DELETE' );
		}
	}

?>